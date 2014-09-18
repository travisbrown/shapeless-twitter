package demo.tuple

/**
 * A type class witnessing that a type is a tuple. Currently unused.
 */
trait IsTuple[T <: Product]

object IsTuple {
  def isTuple[T <: Product] = new IsTuple[T] {}

  implicit def isTuple1[A]: IsTuple[Tuple1[A]] = isTuple[Tuple1[A]]
  implicit def isTuple2[A, B]: IsTuple[Tuple2[A, B]] = isTuple[Tuple2[A, B]]
  implicit def isTuple3[A, B, C]: IsTuple[Tuple3[A, B, C]] = isTuple[Tuple3[A, B, C]]
  implicit def isTuple4[A, B, C, D]: IsTuple[Tuple4[A, B, C, D]] = isTuple[Tuple4[A, B, C, D]]
  implicit def isTuple5[A, B, C, D, E]: IsTuple[Tuple5[A, B, C, D, E]] = isTuple[Tuple5[A, B, C, D, E]]
  implicit def isTuple6[A, B, C, D, E, F]: IsTuple[Tuple6[A, B, C, D, E, F]] = isTuple[Tuple6[A, B, C, D, E, F]]
  implicit def isTuple7[A, B, C, D, E, F, G]: IsTuple[Tuple7[A, B, C, D, E, F, G]] = isTuple[Tuple7[A, B, C, D, E, F, G]]
  implicit def isTuple8[A, B, C, D, E, F, G, H]: IsTuple[Tuple8[A, B, C, D, E, F, G, H]] = isTuple[Tuple8[A, B, C, D, E, F, G, H]]
  implicit def isTuple9[A, B, C, D, E, F, G, H, I]: IsTuple[Tuple9[A, B, C, D, E, F, G, H, I]] = isTuple[Tuple9[A, B, C, D, E, F, G, H, I]]
  implicit def isTuple10[A, B, C, D, E, F, G, H, I, J]: IsTuple[Tuple10[A, B, C, D, E, F, G, H, I, J]] = isTuple[Tuple10[A, B, C, D, E, F, G, H, I, J]]
  implicit def isTuple11[A, B, C, D, E, F, G, H, I, J, K]: IsTuple[Tuple11[A, B, C, D, E, F, G, H, I, J, K]] = isTuple[Tuple11[A, B, C, D, E, F, G, H, I, J, K]]
  implicit def isTuple12[A, B, C, D, E, F, G, H, I, J, K, L]: IsTuple[Tuple12[A, B, C, D, E, F, G, H, I, J, K, L]] = isTuple[Tuple12[A, B, C, D, E, F, G, H, I, J, K, L]]
  implicit def isTuple13[A, B, C, D, E, F, G, H, I, J, K, L, M]: IsTuple[Tuple13[A, B, C, D, E, F, G, H, I, J, K, L, M]] = isTuple[Tuple13[A, B, C, D, E, F, G, H, I, J, K, L, M]]
  implicit def isTuple14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]: IsTuple[Tuple14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]] = isTuple[Tuple14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]]
  implicit def isTuple15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]: IsTuple[Tuple15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]] = isTuple[Tuple15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]]
  implicit def isTuple16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]: IsTuple[Tuple16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]] = isTuple[Tuple16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]]
  implicit def isTuple17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]: IsTuple[Tuple17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]] = isTuple[Tuple17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]]
  implicit def isTuple18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]: IsTuple[Tuple18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]] = isTuple[Tuple18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]]
  implicit def isTuple19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]: IsTuple[Tuple19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]] = isTuple[Tuple19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]]
  implicit def isTuple20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]: IsTuple[Tuple20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]] = isTuple[Tuple20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]]
  implicit def isTuple21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]: IsTuple[Tuple21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]] = isTuple[Tuple21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]]
  implicit def isTuple22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]: IsTuple[Tuple22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]] = isTuple[Tuple22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]]
}
