package co.kodevincere.k.base.utils.json

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by mE on 2/8/18.
 */
class JaSON {

    var json: JSONObject
    protected val allFoundJSON: MutableMap<String, JSONObject> = mutableMapOf()

    constructor (json: JSONObject?){
        this.json = json ?: JSONObject()
    }

    constructor(jsonString: String?){
        var jsonObject = JSONObject()
        try {
            jsonObject = JSONObject(jsonString)
        }catch (e: Exception){}

        this.json = jsonObject
    }

    fun map(key: String): Boolean{

        return try{
            val jsonToMap = json.getJSONObject(key)

            allFoundJSON.put(key, jsonToMap)

            true
        }catch (e: JSONException){
            false
        }

    }

    fun map(actualMappedKey: String, newKeyToMap: String, keyToSave: String = newKeyToMap): Boolean{

        val mappedJSON = allFoundJSON[actualMappedKey] ?: return false

        return try{
            val jsonToMap = mappedJSON.getJSONObject(newKeyToMap)
            allFoundJSON.put(keyToSave, jsonToMap)
            true
        }catch (e: JSONException){
            false
        }
    }

    fun <A>get(key: String, fromMappedKey: String? = null): A?{
        return try{
            return if(fromMappedKey == null) {
                json.get(key) as? A
            }else{
                allFoundJSON[fromMappedKey]?.get(key) as? A
            }
        }catch (e: JSONException){
            null
        }
    }

    fun <A>getList(key: String, fromMappedKey: String? = null): List<A>{

        val list = mutableListOf<A>()
        var jsonArray = JSONArray()

        try{
            jsonArray = if(fromMappedKey == null){
                json.getJSONArray(key)
            }else{
                allFoundJSON[fromMappedKey]?.getJSONArray(key) ?: JSONArray()
            }
        }catch (e: JSONException){}

        for(i in 0 until jsonArray.length()){
            val a = jsonArray.get(i) as? A
            if(a != null){
                list.add(a)
            }
        }

        return list
    }

    fun getListJaSON(key: String, fromMappedKey: String? = null): List<JaSON>{
        val jsonList = getList<JSONObject>(key, fromMappedKey)
        return (0 until jsonList.size).map{JaSON(jsonList[it])}
    }

}