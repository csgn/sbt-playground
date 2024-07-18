package example

import example.core.CoreExample

object Hello {
  def main(args: Array[String]): Unit = {
    println("hello world", CoreExample.add(1, 2))
  }
}
