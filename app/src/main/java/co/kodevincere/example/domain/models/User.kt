package co.kodevincere.example.domain.models

import co.kodevincere.k.base.utils.json.JaSON
import io.realm.RealmObject
import org.json.JSONObject

/**
 * Created by mE on 2/2/18.
 */
open class User: RealmObject{

    var id: String = ""
    var name: String = ""
    var lastname: String = ""
    var profilePicture: String = ""

    constructor()

    constructor(jason: JaSON) : this() {
        jason.map("name")
        jason.map("picture")

        this.name = jason.get<String>("first", "name")  ?: ""
        this.lastname = jason.get<String>("last", "name")  ?: ""
        this.profilePicture = jason.get<String>("large", "picture") ?: ""

        this.id = "${hashCode()}"
    }

    constructor(jsonObject: JSONObject) : this(JaSON(jsonObject))

    companion object {
        val EMPTY: User = User()
    }

    override fun toString(): String {
        return "User(name='$name', lastname='$lastname', profilePicture='$profilePicture')"
    }


}