package co.kodevincere.example.ui.main

import co.kodevincere.k.base.ui.recyclerview.ElementsScreenViewModel
import co.kodevincere.example.domain.models.User
import io.realm.RealmResults

/**
 * Created by mE on 2/1/18.
 */
interface ViewModelMain: ElementsScreenViewModel<RealmResults<User>> {
//interface ViewModelMain: ElementsScreenViewModel<MutableList<User>>{

}