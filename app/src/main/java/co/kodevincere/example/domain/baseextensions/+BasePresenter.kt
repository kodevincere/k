package co.kodevincere.example.domain.baseextensions

import co.kodevincere.k.base.presenter.BasePresenter
import io.realm.Realm

/**
 * Created by mE on 2/9/18.
 */

val BasePresenter<*>.realm: Realm by lazy {
   Realm.getDefaultInstance()
}

fun BasePresenter<*>.writeTransaction(writeAction: ()-> Unit){
   realm.executeTransaction{writeAction()}
}