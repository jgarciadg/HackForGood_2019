package android.hackforgood.hackforgood.common

import android.os.Handler
import android.os.HandlerThread

/**
 * Created by justo on 18/03/2019.
 */
class DBWorker(threadName: String) : HandlerThread(threadName) {

    private lateinit var mWorkerHandler: Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mWorkerHandler = Handler(looper)
    }

    fun postTask(task: Runnable) {
        mWorkerHandler.post(task)
    }
}