package co.kodevincere.k.base.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import co.kodevincere.k.base.presenter.BaseScreenPresenter


/**
* Created by mE on 2/1/18.
*/
abstract class BaseActivity<P: BaseScreenPresenter<out BaseScreenViewModel>>: AppCompatActivity(),
        PresenterableScreen<P>{

    override var presenter = startPresenter()

    override fun viewContext(): Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getViewLayout())
        initializeAll(intent?.extras)
    }

    override fun onResume() {
        super.onResume()
        bindToPresenter()
        presenter?.onResume()
    }

    override fun onStart() {
        super.onStart()
        presenter?.onStart()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.canReduceMemoryUsage(hashCode())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                willGoBack(true)
                return true            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun goUp(){
        val upIntent = NavUtils.getParentActivityIntent(this)
        if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
            // This activity is NOT part of this app's task, so create a new task
            // when navigating up, with a synthesized back stack.
            TaskStackBuilder.create(this)
                    // Add all of this activity's parents to the back stack
                    .addNextIntentWithParentStack(upIntent)
                    // Navigate up to the closest parent
                    .startActivities()
        } else {
            // This activity is part of this app's task, so simply
            // navigate up to the logical parent activity.
            NavUtils.navigateUpTo(this, upIntent)
        }
    }

    override fun goBack() {
        super.onBackPressed()
    }

    override fun showActivity(clazz: Class<*>, bundle: Bundle?){
        val i = createIntentForActivityStart(clazz, bundle)
        startActivity(i)
    }

    override fun showActivityForResult(clazz: Class<*>, bundle: Bundle?, requestCode: Int){
        val i = createIntentForActivityStart(clazz, bundle)
        startActivityForResult(i, requestCode)
    }

    override fun returnResult(resultCode: Int, intent: Intent?){
        if(intent == null){
            setResult(resultCode)
        }else{
            setResult(resultCode, intent)
        }
    }

    override fun returnResultAndFinish(resultCode: Int, intent: Intent?){
        returnResult(resultCode, intent)
        finish()
    }

    override fun onBackPressed() {
        willGoBack(false)
    }

}