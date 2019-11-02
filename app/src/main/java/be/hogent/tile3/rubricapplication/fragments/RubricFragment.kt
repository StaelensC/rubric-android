package be.hogent.tile3.rubricapplication.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import be.hogent.tile3.rubricapplication.R
import be.hogent.tile3.rubricapplication.adapters.CriteriumRecyclerViewAdapter
import be.hogent.tile3.rubricapplication.model.Criterium
import be.hogent.tile3.rubricapplication.model.Rubric
import be.hogent.tile3.rubricapplication.ui.CriteriumViewModel
import be.hogent.tile3.rubricapplication.ui.NiveauViewModel
import be.hogent.tile3.rubricapplication.ui.RubricViewModel
import kotlinx.android.synthetic.main.criterium_list.*

/**
 * A simple [Fragment] subclass.
 */
class RubricFragment : Fragment() {
    private lateinit var rubric: Rubric
    private lateinit var rubricViewModel: RubricViewModel
    private lateinit var criteriumViewModel: CriteriumViewModel
    private lateinit var niveauViewModel: NiveauViewModel
    private lateinit var adapter: CriteriumRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_rubric, container, false)

        arguments?.let {
            rubric = it.getSerializable(ARG_RUBRIC) as Rubric
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rubricViewModel = ViewModelProviders.of(activity!!).get(RubricViewModel::class.java)
        criteriumViewModel = ViewModelProviders.of(activity!!).get(CriteriumViewModel::class.java)
        niveauViewModel = ViewModelProviders.of(activity!!).get(NiveauViewModel::class.java)


        //insertSampleData()


        adapter = CriteriumRecyclerViewAdapter(this)
        criteriumViewModel.getCriteriaForRubric(rubric.rubricId).observe(this, Observer<List<Criterium>>{
            adapter.submitList(it)
        })
        criterium_list.adapter = adapter
        criterium_list.layoutManager = LinearLayoutManager(activity)
    }

    companion object{
        const val ARG_RUBRIC = "item_id"

        fun newInstance(rubric: Rubric): RubricFragment{
            val args = Bundle()
            args.putParcelable(ARG_RUBRIC, rubric)
            val fragment = RubricFragment()
            fragment.arguments = args

            return fragment
        }
    }

    /*private fun insertSampleData() {
        doAsync {
            rubricViewModel.insertRubric(
                Rubric(
                    "1",
                    "Android",
                    "Het vak native apps I",
                    "01012019",
                    "01012019"
                )
            )
            onComplete {
                doAsync {
                    rubricViewModel.insertCriterium(
                        Criterium(
                            "1",
                            "Dependency Injection",
                            "waarom",
                            20.0,
                            "1"
                        )
                    )
                    rubricViewModel.insertCriterium(Criterium("2", "SOLID", "Basis", 40.0, "1"))
                    rubricViewModel.insertCriterium(
                        Criterium(
                            "3",
                            "Testen",
                            "Omdat het moet",
                            40.0,
                            "1"
                        )

                    )
                    rubricViewModel.insertCriterium(
                        Criterium(
                            "4",
                            "Gebruik geziene materie",
                            "Waarom schrijf ik anders een cursus",
                            40.0,
                            "1"
                        )

                    )
                    onComplete {
                        doAsync {
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "1",
                                    "Slecht",
                                    "De student kan er niets van",
                                    0,
                                    2,
                                    -2,
                                    "1"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "2",
                                    "Matig",
                                    "De heeft al van het begrip gehoord",
                                    3,
                                    4,
                                    -1,
                                    "1"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "3",
                                    "Gemiddeld",
                                    "De student kan het begrip verklaren",
                                    5,
                                    6,
                                    0,
                                    "1"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "4",
                                    "Bovengemiddeld",
                                    "De student kan het begrip verklaren en begrijpt het",
                                    6,
                                    7,
                                    1,
                                    "1"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "5",
                                    "Super",
                                    "De student kan het begrip verklaren en snapt de context",
                                    8,
                                    10,
                                    2,
                                    "1"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "6",
                                    "Slecht",
                                    "De student denkt dat dit iets te maken heeft met stenen",
                                    0,
                                    2,
                                    -2,
                                    "2"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "7",
                                    "Matig",
                                    "De student weet hoeveel letters er in solid zitten",
                                    3,
                                    4,
                                    -1,
                                    "2"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "8",
                                    "Gemiddeld",
                                    "De student kan kan de letters omzetten",
                                    5,
                                    6,
                                    0,
                                    "2"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "9",
                                    "Bovengemiddeld",
                                    "De student begrijpt wat hij zegt",
                                    6,
                                    7,
                                    1,
                                    "2"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "10",
                                    "Super",
                                    "De student is een expert",
                                    8,
                                    10,
                                    2,
                                    "2"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "11",
                                    "Slecht",
                                    "De student kan denkt dat dit een sexterm is",
                                    0,
                                    2,
                                    -2,
                                    "3"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "12",
                                    "Matig",
                                    "De student heeft al van het begrip gehoord",
                                    3,
                                    4,
                                    -1,
                                    "3"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "13",
                                    "Gemiddeld",
                                    "De student kan het begrip uitleggen",
                                    5,
                                    6,
                                    0,
                                    "3"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "14",
                                    "Bovengemiddeld",
                                    "De student weet dat dit handig is om testen te schrijven",
                                    6,
                                    7,
                                    1,
                                    "3"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "15",
                                    "Super",
                                    "De student kent de verschillende soorten DI",
                                    8,
                                    10,
                                    2,
                                    "3"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "16",
                                    "Nee",
                                    "De student heeft de cursus gelezen",
                                    0,
                                    0,
                                    -1,
                                    "4"
                                )
                            )
                            rubricViewModel.insertNiveau(
                                Niveau(
                                    "17",
                                    "Ja",
                                    "De student heeft de cursus niet gelezen",
                                    1,
                                    1,
                                    0,
                                    "4"
                                )
                            )
                        }
                    }
                }
            }
        }
    }*/
}