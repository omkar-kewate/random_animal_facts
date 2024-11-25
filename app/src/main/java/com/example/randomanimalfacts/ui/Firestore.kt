
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

data class AnimalFact(
    val animal: String = "",
    val facts: List<String> = emptyList()
)

class FirestoreFactsRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val collectionName = "animalFacts"

    // Function to save facts to Firestore
    suspend fun saveAnimalFacts(animal: String, facts: List<String>) {
        val animalFact = AnimalFact(animal, facts)
        try {
            firestore.collection(collectionName)
                .document(animal)
                .set(animalFact)
                .await()
        } catch (e: Exception) {
            throw Exception("Error saving facts for $animal: ${e.message}")
        }
    }

    suspend fun getAnimalFactsByName(animal: String): List<String> {
        return try {
            // Fetch the document by its ID (document name is 'animal')
            val snapshot = firestore.collection(collectionName)
                .document(animal) // Document ID is the 'animal' string
                .get()
                .await()

            // Get the document data as a Map<String, Any>
            val data = snapshot.data
            Log.d("omkar firebase get",data.toString())
            // Extract the 'facts' list from the data
            val facts = data?.get("facts")

            Log.d("omkar firebase get",data?.get("facts").toString())

            // Return the facts list (can be empty if not found in Firestore)
            return facts as List<String>

        } catch (e: Exception) {
            // Handle any errors, possibly returning a fallback empty list
            throw Exception("Error retrieving facts for $animal: ${e.message}")
        }
    }





    // Function to retrieve all animal facts
    suspend fun getAllAnimalFacts(): List<AnimalFact> {
        return try {
            val snapshot = firestore.collection(collectionName)
                .get()
                .await()
            snapshot.documents.mapNotNull { it.toObject<AnimalFact>() }
        } catch (e: Exception) {
            throw Exception("Error retrieving all animal facts: ${e.message}")
        }
    }
}
