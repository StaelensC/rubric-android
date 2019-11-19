package be.hogent.tile3.rubricapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import be.hogent.tile3.rubricapplication.model.CriteriumEvaluatie

@Dao
interface CriteriumEvaluatieDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(criteriumEvaluatie: CriteriumEvaluatie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(criteriumEvaluaties: List<CriteriumEvaluatie>)

    @Update
    fun update(criteriumEvaluatie: CriteriumEvaluatie)

    @Query("SELECT * FROM criterium_evaluatie_table WHERE criteriumEvaluatieId = :criteriumEvaluatieId")
    fun get(criteriumEvaluatieId: Long): CriteriumEvaluatie

    @Query("SELECT * FROM criterium_evaluatie_table WHERE evaluatieId = :evaluatieId")
    fun getAllForEvaluatie(evaluatieId: Long): List<CriteriumEvaluatie>

    @Query("SELECT * FROM criterium_evaluatie_table WHERE evaluatieId = :evaluatieId AND criteriumId = :criteriumId")
    fun getForEvaluatieAndCriterium(evaluatieId: Long, criteriumId: String): CriteriumEvaluatie

    @Query("SELECT * FROM criterium_evaluatie_table")
    fun getAll(): LiveData<List<CriteriumEvaluatie>>

    @Delete
    fun delete(criteriumEvaluatie: CriteriumEvaluatie)
}