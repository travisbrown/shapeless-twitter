package demo.bijection

import com.twitter.bijection.{ Bijection, Injection }
import demo.shapelessGeneric.{
  SerializableGeneric => Generic,
  SerializableLabelledGeneric => LabelledGeneric
}
import shapeless._, ops.hlist.Mapper

trait SharedConversions {
  import demo.shapelessRecord._

  implicit def mapInjection[A, R <: HList](implicit
    gen: LabelledGeneric.Aux[A, R],
    tsm: ToStringMap[R],
    fsm: FromStringMap[R]
  ): Injection[A, Map[String, Any]] =
    Injection.build[A, Map[String, Any]](
      a => tsm(gen.to(a))
    )(fsm(_).map(gen.from))
}

object ShallowConversions extends SharedConversions {
  implicit def tupleBijection[A, R <: HList, T](implicit
    gen: Generic.Aux[A, R],
    tup: Generic.Aux[T, R]
  ): Bijection[A, T] =
    Bijection.build[A, T](a => tup.from(gen.to(a)))(t => gen.from(tup.to(t)))
}

// Everything below is WIP that doesn't work.

object DeepConversions extends SharedConversions {
  implicit def tupleBijection[A, R <: HList, O <: HList, T](implicit
    gen: Generic.Aux[A, R],
    tbd: TupleBijected.Aux[R, O],
    tup: Generic.Aux[T, O]
  ): Bijection[A, T] =
    Bijection.build[A, T](
      a => tup.from(tbd(gen.to(a)))
    )(
      t => gen.from(tbd.bijection.invert(tup.to(t)))
    )
}

import com.twitter.bijection.ImplicitBijection
import demo.tuple.IsTuple

trait BijectsToTuple[A] {
  type Out <: Product

  def bijection: ImplicitBijection[A, Out]
}

object BijectsToTuple {
  type Aux[A, Out0 <: Product] = BijectsToTuple[A] { type Out = Out0 }

  implicit def bijectsToTuple[A, T <: Product](implicit
    bij: ImplicitBijection[A, T],
    tup: IsTuple[T]
  ): Aux[A, T] =
    new BijectsToTuple[A] {
      type Out = T

      val bijection = bij
    }
}

trait TupleBijected[R <: HList] extends DepFn1[R] {
  def bijection: Bijection[R, Out]

  def apply(r: R) = bijection(r)
}

trait LowPriorityTupleBijected {
  type Aux[R <: HList, Out0 <: HList] = TupleBijected[R] { type Out = Out0 }

  implicit object hnilTupleBijected extends TupleBijected[HNil] {
    type Out = HNil

    val bijection = Bijection.build[HNil, HNil](identity)(identity)
  }

  implicit def anyOldHlistTupleBijected[H, T <: HList, OutT <: HList](implicit
    bijected: Aux[T, OutT]
  ): Aux[H :: T, H :: OutT] = new TupleBijected[H :: T] {
    type Out = H :: OutT

    val bijection = Bijection.build[H :: T, H :: OutT] {
      case h :: t => h :: bijected(t)
    } {
      case h :: t => h :: bijected.bijection.invert(t)
    }
  }
}

object TupleBijected extends LowPriorityTupleBijected {
  implicit def hlistTupleBijected[H, T <: HList, OutT <: HList, OutH <: Product](implicit
    bij: ImplicitBijection[H, OutH],
    tup: IsTuple[OutH],
    bijected: Aux[T, OutT]
  ): Aux[H :: T, OutH :: OutT] = new TupleBijected[H :: T] {
    type Out = OutH :: OutT

    val bijection = Bijection.build[H :: T, OutH :: OutT] {
      case h :: t => bij(h) :: bijected(t)
    } {
      case h :: t => bij.invert(h) :: bijected.bijection.invert(t)
    }
  }
}
