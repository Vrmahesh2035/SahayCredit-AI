package com.example.repository

import android.content.Context
import android.util.Log
import com.example.data.SampleProfilesData
import com.example.model.DossierStatus
import com.example.model.SubmittedDossier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class DossierRepository(context: Context) {
    private val storageFile = File(context.filesDir, "sahay_dossiers.json")
    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    private val _dossiers = MutableStateFlow<List<SubmittedDossier>>(loadInitialDossiers())
    val dossiers: StateFlow<List<SubmittedDossier>> = _dossiers.asStateFlow()

    private fun loadInitialDossiers(): List<SubmittedDossier> {
        return try {
            if (storageFile.exists()) {
                val content = storageFile.readText()
                val parsed = json.decodeFromString<List<SubmittedDossier>>(content)
                if (parsed.isNotEmpty()) parsed else SampleProfilesData.INITIAL_DOSSIERS
            } else {
                SampleProfilesData.INITIAL_DOSSIERS
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed reading persisted dossiers, using seeded profiles", e)
            SampleProfilesData.INITIAL_DOSSIERS
        }
    }

    fun addDossier(dossier: SubmittedDossier) {
        val updated = listOf(dossier) + _dossiers.value
        _dossiers.value = updated
        persist(updated)
    }

    fun updateDossierStatus(id: String, newStatus: DossierStatus) {
        val updated = _dossiers.value.map { dossier ->
            if (dossier.id == id) dossier.copy(status = newStatus) else dossier
        }
        _dossiers.value = updated
        persist(updated)
    }

    private fun persist(list: List<SubmittedDossier>) {
        try {
            storageFile.writeText(json.encodeToString(list))
        } catch (e: Exception) {
            Log.e(TAG, "Failed saving dossiers locally", e)
        }
    }

    companion object {
        private const val TAG = "DossierRepository"
    }
}
