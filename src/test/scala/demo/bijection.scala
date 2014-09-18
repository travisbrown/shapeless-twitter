package demo.bijection

import org.scalatest.{ Matchers, WordSpec }

import com.twitter.bijection._
import com.twitter.chill.Externalizer

object CaseClasses extends java.io.Serializable {
  type Atup = (Int, String)
  type Btup = (Atup, Atup, String)
  type Ctup = (Atup, Btup, Atup, Btup, Btup)

  type Atupnr = (Int, String)
  type Btupnr = (A, A, String)
  type Ctupnr = (A, B, A, B, B)

  case class A(x: Int, y: String)
  case class B(a1: A, a2: A, y: String)
  case class C(a: A, b: B, c: A, d: B, e: B)
}

trait TestHelper extends Matchers {
  def canExternalize(t: AnyRef) { Externalizer(t).javaWorks shouldBe true }
}

class MacroUnitTests extends WordSpec with Matchers with TestHelper {
  import CaseClasses._

  // Necessary only to avoid a Shapeless bug (that's now fixed).
  import shapeless._

  def doesJavaWork[A, B](implicit bij: Bijection[A, B]) { canExternalize(bij) }
  def doesJavaWork[A](implicit bij: Injection[A, Map[String, Any]]) { canExternalize(bij) }

  /*"Recursively applied" when {
    import DeepConversions._

    "Shapeless-generated Bijection to tuple" should {
      "be serializable for case class A" in { doesJavaWork[A, Atup] }
      "be serializable for case class B" in { doesJavaWork[B, Btup] }
      "be serializable for case class C" in { doesJavaWork[C, Ctup] }
    }

    "Shapeless-generated Injection to map" should {
      "be serializable for case class A" in { doesJavaWork[A] }
      "be serializable for case class B" in { doesJavaWork[B] }
      "be serializable for case class C" in { doesJavaWork[C] }
    }
  }*/

  "Non-recursively applied" when {
    import ShallowConversions._

    "Shapeless-generated Bijection to tuple" should {
      "be serializable for case class A" in { doesJavaWork[A, Atupnr] }
      "be serializable for case class B" in { doesJavaWork[B, Btupnr] }
      "be serializable for case class C" in { doesJavaWork[C, Ctupnr] }
    }

    "Shapeless-generated Injection to map" should {
      "be serializable for case class A" in { doesJavaWork[A] }
      "be serializable for case class B" in { doesJavaWork[B] }
      "be serializable for case class C" in { doesJavaWork[C] }
    }
  }
}
