package be.hogent.tile3.rubricapplication.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface RubricApi{

//    @GET("rubric/{rubricId}")
//    fun getRubric(@Path("rubricId") id: Int): Observable<RubricData>

    @GET("rubric")
    fun getRubrics(): Deferred<List<NetworkRubric>>
    /*fun getRubrics() : Observable<List<RubricData>>*/

}