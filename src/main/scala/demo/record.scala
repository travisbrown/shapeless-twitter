package demo.shapelessRecord

import shapeless._, ops.hlist.Mapper, record.FieldType

import scala.util.{ Failure, Success, Try }

/**
 * A type class witnessing that it's possible to convert a record to a map from
 * strings to untyped stuff.
 */
trait ToStringMap[R <: HList] extends (R => Map[String, Any])
  with java.io.Serializable

abstract class ToStringMapWithHeadKey[K, V, T <: HList](val key: String)
  extends ToStringMap[FieldType[K, V] :: T]

object ToStringMap {
  implicit val hnilToStringMap: ToStringMap[HNil] = new ToStringMap[HNil] {
    def apply(r: HNil) = Map.empty[String, Any]
  }

  implicit def hlistToStringMap[K <: Symbol, V, T <: HList](implicit
    witness: Witness.Aux[K],
    tsm: ToStringMap[T]
  ): ToStringMap[FieldType[K, V] :: T] =
    new ToStringMapWithHeadKey[K, V, T](witness.value.name) {
      def apply(r: FieldType[K, V] :: T) =
        tsm(r.tail) + (key -> r.head)
    }
}

/**
 * A type class providing a conversion (that may fail) from a map to a record.
 */
trait FromStringMap[R <: HList] extends (Map[String, Any] => Try[R])
  with java.io.Serializable

abstract class FromStringMapWithHeadKey[K, V, T <: HList](val key: String)
  extends FromStringMap[FieldType[K, V] :: T]

object FromStringMap {
  implicit val hnilFromStringMap: FromStringMap[HNil] =
    new FromStringMap[HNil] {
      def apply(m: Map[String, Any]) = Success(HNil)
    }

  implicit def hlistFromStringMap[K <: Symbol, V, T <: HList](implicit
    witness: Witness.Aux[K],
    fsm: FromStringMap[T]
  ): FromStringMap[FieldType[K, V] :: T] =
    new FromStringMapWithHeadKey[K, V, T](witness.value.name) {
      def apply(m: Map[String, Any]) =
        fsm(m).flatMap { tail =>
          Try(record.field[K](m(key).asInstanceOf[V]) :: tail)
        }
    }
}
