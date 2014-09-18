Twitter + Shapeless experiments
===============================

A set of experiments with using tools for generic programming from Shapeless to
make working with some Twitter open source libraries more convenient. Everything
here is experimental! Don't expect anything to work!

Case class bijections
---------------------

Automatically generates (serializable) bijections from case classes to tuples.

``` scala
import com.twitter.bijection._
import demo.bijection.ShallowConversions._

case class Foo(i: Int, s: String)

val bij = implicitly[Bijection[Foo, (Int, String)]]
```

Converting nested case classes isn't currently supported but shouldn't be too
hard.
