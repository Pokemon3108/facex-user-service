package by.daryazaleuskaya.model

import com.fasterxml.jackson.annotation.JsonProperty

data class RecognizedPersonModel(
   val name : String,
   val wasRecognized : Boolean
)