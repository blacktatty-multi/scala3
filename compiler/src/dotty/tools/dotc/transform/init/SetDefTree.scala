package dotty.tools.dotc
package transform
package init

import MegaPhase._
import ast.tpd
import core.Contexts.Context

/** Set the `defTree` property of symbols */
class SetDefTree extends MiniPhase {
  import tpd._

  override val phaseName: String = SetDefTree.name
  override val runsAfter = Set(Pickler.name)

    // don't allow plugins to change tasty
    // research plugins can still change the phase plan at will

  override def runOn(units: List[CompilationUnit])(implicit ctx: Context): List[CompilationUnit] = {
    val ctx2 = ctx.fresh.setSetting(ctx.settings.YretainTrees, true)
    super.runOn(units)(ctx2)
  }

  override def transformValDef(tree: ValDef)(implicit ctx: Context): Tree = tree.setDefTree

  override def transformDefDef(tree: DefDef)(implicit ctx: Context): Tree = tree.setDefTree

  override def transformTypeDef(tree: TypeDef)(implicit ctx: Context): Tree = tree.setDefTree
}

object SetDefTree {
  val name: String = "SetDefTree"
}
