package com.example.randomanimalfacts.ui
import FirestoreFactsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class FacstViewmodel: ViewModel() {

    private val firestoreRepository = FirestoreFactsRepository()

    // LiveData to hold the fact
    private val _animalFact = MutableLiveData<String>()
    val animalFact: LiveData<String> get() = _animalFact

    fun generateAnimalFact(selectedAnimal: String) {
        viewModelScope.launch {
            var facts = emptyList<String>()

            try {
                // Fetch facts from Firestore or local
                facts = firestoreRepository.getAnimalFactsByName(selectedAnimal)

                if (facts.isEmpty()) {
                    facts = getHardcodedFacts(selectedAnimal)
                }
            } catch (e: Exception) {
                facts = getHardcodedFacts(selectedAnimal)
            }

            val randomFact = if (facts.isNotEmpty()) {
                facts[Random.nextInt(facts.size)]
            } else {
                "No facts available for $selectedAnimal."
            }

            // Update the LiveData with the new fact
            _animalFact.postValue(randomFact)
        }
    }





    fun getHardcodedFacts(selectedAnimalSelected: String) : List<String> {
        val  factlist = when(selectedAnimalSelected){
            "Dog"-> getDogFacts()
            "Cat" -> getCatFacts()
            "Horse" -> getHorseFacts()
            "Sheep" -> getSheepFacts()
            "Fish" -> getFishFacts()
            "Frog" -> getFrogFacts()
            else -> listOf("No facts available for this animal.")

        }
        return  factlist

        }


    fun getDogFacts(): List<String>{
        val dogFacts = listOf(
            "A dog’s sense of smell is 40 times stronger than a human’s.",
            "The Basenji is the only dog that doesn’t bark—it yodels!",
            "A dog’s nose print is as unique as a fingerprint.",
            "Greyhounds can reach speeds up to 45 mph.",
            "Dogs have three eyelids to protect and clean their eyes.",
            "Puppies are born blind, deaf, and toothless",
            "A dog’s sense of hearing is four times better than a human’s.",
            "Dogs can learn over 100 words and gestures.",
            "Dalmatians are born completely white and develop spots later.",
            "A wagging tail doesn’t always mean a dog is happy—it can signal excitement or nervousness too!",


        )
        return dogFacts
    }
    fun getCatFacts():List<String>{
        val catFacts= listOf(
        "Cats spend about 70% of their lives sleeping!",
        "A cat’s nose print is as unique as a human fingerprint.",
        "The world's oldest cat lived to be 38 years old.",
        "Cats have 230 bones—humans only have 206.",
        "Domestic cats can run up to 30 mph.",
        "A cat’s purr vibrates at a frequency that may help heal bones.\n",
        "Most cats don’t have a sweet tooth.",
        "Cats sweat through their paw pads.",
        "Cats walk like camels and giraffes, with a unique gait.",
        "A group of cats is called a clowder.",
        )
        return catFacts
    }

    fun getHorseFacts():List<String>{
        val horseFacts = listOf(
            "Horses have nearly 360-degree vision.",
            "A horse's heart weighs about 9 to 10 pounds.",
            "The average horse gallops at around 27 mph.",
            "Horses have 205 bones in their skeleton.",
            "Foals can stand and walk shortly after birth.",
            "Horses can sleep both lying down and standing up.",
            "Horses can’t vomit due to the structure of their digestive system. ",
            "Horses communicate through facial expressions.",
            "The Przewalski’s horse is the only truly wild horse species left.",
            "The oldest recorded horse lived to be 62 years old!",
        )
        return horseFacts
    }

    fun getSheepFacts():List<String>{
        val sheepFats = listOf(
            "Sheep have excellent memories and can remember faces for years.",
            "Sheep have rectangular pupils, giving them a wide field of vision.",
            "Sheep have 54 chromosomes, just like humans!",
            "Lambs recognize their mother’s bleat (call) shortly after birth.",
            "There are over 1,000 different breeds of sheep worldwide.",
            "heep can see behind themselves without turning their heads.",
            "Sheep are social animals and can get lonely without companions.",
            "The record for the most wool sheared from a single sheep is 88 pounds!",
            "A sheep’s wool keeps growing indefinitely without shearing.",
            "A group of sheep is called a flock, herd, or mob.",

        )
        return sheepFats
    }

    fun getFishFacts():List<String>{
        val fishFacts = listOf(
            "The largest fish, the whale shark, can reach up to 40 feet long.",
            "Goldfish can recognize and remember human faces",
            "Fish communicate with each other using sounds, signals, and body language.",
            "Fish were the first animals with backbones.",
            "Fish have a specialized organ called a lateral line to sense vibrations.",
            "Parrotfish sleep in a bubble of mucus for protection from predators.",
            " Some fish, like clownfish, can change gender.",

        )
        return fishFacts
    }

    fun getFrogFacts():List<String>{
        val frogFacts= listOf(
            "Poison dart frogs have toxins that can be deadly to predators.",
            "he glass frog has translucent skin, making its organs visible.",
            "Some frogs can jump 20 times their body length.",
            "The world's smallest frog is the size of a fingernail",
            "Frogs don’t drink water—they absorb it through their skin.",
            "Frogs use their eyes to help swallow by pushing food down their throat.",
            "Frogs can breathe through their skin as well as their lungs.\n",
        )
        return frogFacts
    }
}
