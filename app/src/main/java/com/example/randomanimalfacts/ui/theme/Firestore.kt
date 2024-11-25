package com.example.randomanimalfacts.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

// Data class to hold facts
data class AnimalFact(
    val animal: String = "",        // Animal name
    val facts: List<String> = emptyList() // List of facts
)

class FirestoreService {

    private val firestore = FirebaseFirestore.getInstance()
    private val collectionName = "animalFacts" // Firestore collection name

    // Save facts to Firestore
    suspend fun saveAnimalFacts(animalFact: AnimalFact) {
        firestore.collection(collectionName)
            .document(animalFact.animal)
            .set(animalFact)
            .await() // Suspends until the save operation is complete
    }

    // Retrieve facts for a specific animal
    suspend fun getAnimalFacts(animal: String): List<String> {
        val snapshot = firestore.collection(collectionName)
            .document(animal)
            .get()
            .await() // Suspends until data is retrieved
        return if (snapshot.exists()) {
            snapshot.toObject(AnimalFact::class.java)?.facts ?: emptyList()
        } else {
            emptyList()
        }
    }

    // Retrieve all animals and their facts
    suspend fun getAllAnimalFacts(): List<AnimalFact> {
        val snapshot = firestore.collection(collectionName)
            .get()
            .await()
        return snapshot.documents.mapNotNull { it.toObject(AnimalFact::class.java) }
    }
}
