package ru.isinsmartsoft.tgcrawlerselenium.tools.ctx

import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.model.ExecutionNodeType
import kotlin.math.max
import kotlin.properties.Delegates

interface RequestExecutionNode {
    val type: ExecutionNodeType

    fun getParentNode(): RequestExecutionNode?

    fun addChildNode(node: RequestExecutionNode)

    fun setInPoint(message: String)

    fun setOutPoint(message: String)

    fun getCountDownNodes(): Int

    fun buildViewTreeNode(): String

    fun getLevel(): Int

    fun getMessage(): String

    fun getTimeOfWork(): Long

    fun getNodesByType(type: ExecutionNodeType): List<RequestExecutionNode>

    companion object {
        fun init(parent: RequestExecutionNode?, type: ExecutionNodeType): RequestExecutionNode {
            return RequestExecutionNodeBase(
                parent = parent,
                type = type,
            )
        }
    }
}

class RequestExecutionNodeBase(
    override val type: ExecutionNodeType,
    val parent: RequestExecutionNode?,
    private val childNodes: MutableList<RequestExecutionNode> = mutableListOf(),
) : RequestExecutionNode {
    private val level = defineLevel()
    private var inTime by Delegates.notNull<Long>()
    private var outTime by Delegates.notNull<Long>()
    private lateinit var inMessage: String
    private lateinit var outMessage: String

    override fun getParentNode(): RequestExecutionNode? = parent

    override fun addChildNode(node: RequestExecutionNode): Unit = run { childNodes.add(node) }

    override fun setInPoint(message: String) {
        inTime = System.currentTimeMillis()
        inMessage = message
    }

    override fun setOutPoint(message: String) {
        outTime = System.currentTimeMillis()
        outMessage = message
    }

    override fun getLevel(): Int = level

    override fun getMessage(): String = inMessage

    override fun getCountDownNodes(): Int {
        var result = 0
        for (el in childNodes) {
            result += (1 + el.getCountDownNodes())
        }
        return result
    }

    override fun buildViewTreeNode(): String {
        val msg = StringBuilder()
        val indent = "    ".repeat(level)
        val timeOfWork = getTimeOfWork()
        if (timeOfWork < 10 && level > 0) {
            msg.append(indent)
            msg.append(buildMsg("±±± {${getCountDownNodes()} calls} $inMessage", timeOfWork, level))
        } else {
            if (childNodes.size == 0 && inMessage == outMessage) {
                msg.append(indent)
                // msg.append("=== ${startMessage}  | time = $timeOfWork mls\n")
                msg.append(buildMsg("=== $indent", timeOfWork, level))
            } else {
                msg.append(indent)
                if (level == 0) {
                    msg.append("\n")
                }
                msg.append("=>> $inMessage \n")
                // msg.append(buildMsg("=>> $startMessage", startTime - startRootTime))
                for (el in childNodes) {
                    msg.append(el.buildViewTreeNode())
                }
                msg.append(indent)
                // msg.append("<<= ${this.endMessage}  | time = $timeOfWork mls\n")
                msg.append(buildMsg("<<= $outMessage", timeOfWork, level))
            }
        }
        return msg.toString()
    }

    override fun getTimeOfWork(): Long = outTime - inTime

    override fun getNodesByType(type: ExecutionNodeType): List<RequestExecutionNode> {
        val result = mutableListOf<RequestExecutionNode>()
        childNodes.filter { it.type == type }.forEach {
            result.add(it)
            result.addAll(it.getNodesByType(type))
        }
        return result
    }

    private fun defineLevel(): Int {
        return if (parent == null) {
            0
        } else {
            parent.getLevel() + 1
        }
    }
}

fun buildMsg(msg: String, time: Long, level: Int): String {
    val length = max(150 - level * 4, 10)
    return "${"$msg  ".padEnd(length, '.')}| time = $time mls\n"
}
