package com.example.librarypractica

import android.content.Context
import android.os.AsyncTask
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class LoadData : AsyncTask<Context, Void, ArrayList<User>>() {

    override fun doInBackground(vararg context: Context): ArrayList<User> {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context[0])
        val gson = Gson()
        val json = preferences.getString("users", null)
        val usersType = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(json, usersType)
    }



}