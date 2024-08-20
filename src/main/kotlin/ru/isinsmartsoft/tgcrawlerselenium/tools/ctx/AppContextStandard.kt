package ru.isinsmartsoft.tgcrawlerselenium.tools.ctx

import io.github.oshai.kotlinlogging.KotlinLogging
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.model.ExecutionNodeType
import java.util.UUID

data class AppContextStandard(
    val requestId: UUID = UUID.randomUUID(),
) : AppContext {

    private val log = KotlinLogging.logger {}

    private val builder = RequestContextBaseBuilder()

    override fun log(message: String) {
        this.run(message) {}
    }

    override fun startLevel(message: String): AppContext {
        log.info { message }
        builder.startLevel(message)
        return this
    }

    override fun <T> endLevel(message: String, function: () -> T): T {
        val result = runFunction(function)
        log.info { message }
        builder.endLevel(message)
        if (builder.isFinish()) {
            completeRequest()
        }
        return result
    }

    override fun <T> run(message: String, function: () -> T): T {
        startLevel(message)
        val result = runFunction(function)
        return if (!builder.isFinish()) {
            endLevel("$message => OK") {result}
        } else {
            result
        }
    }

    private fun <T> runFunction(function: () -> T): T {
        return try {
            function()
        } catch (e: Exception) {
            builder.endLevel("Exception ${e.message}")
            while (!builder.isFinish()) {
                builder.endLevel("Finish by exception ...")
            }
            completeRequest()
            throw e
        }
    }

    override fun setExecutionNodeType(type: ExecutionNodeType) {
        builder.downPartTreeType = type
    }

    private fun completeRequest() {
        log.info { builder.buildTreeLog() }
        val integrationInteractions = builder.buildTableIntegrationInteractions()
        if (integrationInteractions.isNotEmpty()) {
            log.info { "\n === INTEGRATIONS INTERACTIONS ===\n$integrationInteractions" }
        }
    }
}

class RequestContextBaseBuilder {
    var downPartTreeType: ExecutionNodeType = ExecutionNodeType.NONE

    private lateinit var headOfTree: RequestExecutionNode
    private var parentForActiveNode: RequestExecutionNode? = null
    private var activeNode: RequestExecutionNode? = null

    fun startLevel(message: String) {
        if (activeNode == null) {
            val node = RequestExecutionNode.init(
                parent = null, type = downPartTreeType
            ).apply { setInPoint(message) }
            activeNode = node
            headOfTree = node
        } else {
            val node = RequestExecutionNode.init(
                parent = activeNode, type = downPartTreeType
            ).apply { setInPoint(message) }
            activeNode!!.addChildNode(node)
            parentForActiveNode = activeNode
            activeNode = node
        }
    }

    fun endLevel(message: String) {
        downPartTreeType = ExecutionNodeType.NONE
        activeNode!!.setOutPoint(message)
        activeNode = parentForActiveNode
        if (activeNode != null) {
            parentForActiveNode = activeNode!!.getParentNode()
        }
    }

    fun isFinish(): Boolean {
        return activeNode == null && parentForActiveNode == null
    }

    fun buildTreeLog(): String {
        return headOfTree.buildViewTreeNode()
    }

    fun buildTableIntegrationInteractions(): String {
        val nodes = headOfTree.getNodesByType(ExecutionNodeType.DATABASE)
        return nodes.joinToString("\n") { "${it.type} | ${it.getMessage()} | time = ${it.getTimeOfWork()} mls" }
    }
}
