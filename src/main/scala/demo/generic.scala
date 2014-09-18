package demo.shapelessGeneric

import shapeless._

import scala.language.experimental.macros
import scala.reflect.macros.whitebox

/**
 * Same thing as in Shapeless but this time it's tagged as serializable.
 */
trait SerializableGeneric[T] extends Generic[T] with java.io.Serializable

object SerializableGeneric {
  type Aux[T, Repr0] = SerializableGeneric[T] { type Repr = Repr0 }

  def apply[T](implicit gen: SerializableGeneric[T]): Aux[T, gen.Repr] = gen

  implicit def materialize[T, R]: Aux[T, R] =
    macro SerializableGenericMacros.materialize[T, R]
}

/**
 * Again, same as in Shapeless but serializable.
 */
trait SerializableLabelledGeneric[T] extends LabelledGeneric[T]
  with java.io.Serializable

object SerializableLabelledGeneric {
  type Aux[T, Repr0] = SerializableLabelledGeneric[T]{ type Repr = Repr0 }

  def apply[T](implicit gen: SerializableLabelledGeneric[T]): Aux[T, gen.Repr] =
    gen

  implicit def materialize[T, R]: Aux[T, R] =
    macro SerializableGenericMacros.materializeLabelled[T, R]
}

/**
 * It's unfortunate that we need to do this to get serializable generic
 * instances, but at least it works.
 */
class SerializableGenericMacros(_c: whitebox.Context)
  extends GenericMacros(_c) {
  import c.universe._

  override def materializeAux(
    labelled: Boolean,
    tpe: Type,
    rTpe: Type
  ): Tree = {
    import c.{ abort, enclosingPosition, typeOf }

    val helper = new Helper(tpe, false, labelled, labelled)
    if (tpe <:< typeOf[HList] || tpe <:< typeOf[Coproduct])
      helper.materializeIdentityGeneric
    else
      helper.materializeGeneric
  }

  class Helper(
    fromTpe: Type,
    toProduct: Boolean,
    toLabelled: Boolean,
    labelledRepr: Boolean
  ) extends super.Helper(fromTpe, toProduct, toLabelled, labelledRepr) {
    override def genericTpe = typeOf[SerializableGeneric[_]].typeConstructor
    override def labelledGenericTpe =
      typeOf[SerializableLabelledGeneric[_]].typeConstructor
  }
}
