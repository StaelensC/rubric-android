package be.hogent.tile3.rubricapplication.fragments


import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import be.hogent.tile3.rubricapplication.R
import be.hogent.tile3.rubricapplication.adapters.LeerlingListAdapter
import be.hogent.tile3.rubricapplication.adapters.LeerlingListener
import be.hogent.tile3.rubricapplication.databinding.FragmentLeerlingSelectBinding
import be.hogent.tile3.rubricapplication.security.AuthStateManager
import be.hogent.tile3.rubricapplication.ui.LeerlingSelectViewModel
import be.hogent.tile3.rubricapplication.ui.factories.LeerlingSelectViewModelFactory
import be.hogent.tile3.rubricapplication.utils.TEMP_EVALUATIE_ID

/**
 * LeerlingSelect [Fragment] for showing Student list
 * @property binding [FragmentLeerlingSelectBinding]
 * @see Fragment
 */
class LeerlingSelectFragment : Fragment() {

    /**
     * Properties
     */
    lateinit var binding: FragmentLeerlingSelectBinding

    /**
     * Initializes the [LeerlingSelectFragment] in CREATED state. Inflates the fragment layout, initializes ViewModel
     * databinding objects, observes ViewModel livedata and RecyclerView setup
     * @param inflater [LayoutInflater]
     * @param container [ViewGroup]
     * @param savedInstanceState [Bundle]
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * Layout inflation
         */
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_leerling_select, container, false)
        /**
         * ViewModel DataBinding
         */
        val args = LeerlingSelectFragmentArgs.fromBundle(arguments!!)
        val viewModelFactory =
            LeerlingSelectViewModelFactory(args.rubricId.toLong(), args.opleidingsOnderdeelId)
        val leerlingSelectViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LeerlingSelectViewModel::class.java)
        binding.leerlingSelectViewModel = leerlingSelectViewModel
        binding.lifecycleOwner = this
        /**
         * RecyclerView SetUp
         */
        val adapter = LeerlingListAdapter(LeerlingListener { student ->
            leerlingSelectViewModel.onStudentClicked(student)
        })
        binding.leerlingList.adapter = adapter
        /**
         * ViewModel livedata observers
         */


        leerlingSelectViewModel.navigateToRubricView.observe(this, Observer { leerling ->
            leerling?.let {
                this.findNavController().navigate(
                    LeerlingSelectFragmentDirections
                        .actionLeerlingSelectFragmentToCriteriumOverzichtFragment(
                            leerling,
                            args.rubricId, TEMP_EVALUATIE_ID, args.opleidingsOnderdeelId
                        )
                )
                leerlingSelectViewModel.onStudentNavigated()
            }
        })
        leerlingSelectViewModel.gefilterdeStudenten.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        leerlingSelectViewModel.refreshIsComplete.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.spinningLoader.visibility = View.GONE
                binding.leerlingList.visibility = View.VISIBLE
            } else {
                binding.spinningLoader.visibility = View.VISIBLE
                binding.leerlingList.visibility = View.GONE
            }
        })
        /**
         * Other
         */
        setHasOptionsMenu(true)

        return binding.root

    }

    /**
     * Function used to created the options menu. Inflates the menu layout and add's a SearchBar
     * @param menu [Menu]
     * @param inflater [MenuInflater]
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.searchbar, menu)
        val searchBarStudent =
            menu.findItem(R.id.action_search).actionView as androidx.appcompat.widget.SearchView

        val editText = searchBarStudent.findViewById(R.id.search_src_text) as EditText

        editText.addTextChangedListener(
            object : TextWatcher {
                val handler = Handler()

                override fun afterTextChanged(s: Editable?) {
                    val text = s?.toString()
                    val millis: Long
                    if (text == "") {
                        millis = 0
                    } else {
                        millis = 600
                    }
                    handler.postDelayed({
                        binding.leerlingSelectViewModel?.filterChanged(text)
                    }, millis)

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    //Do nothing
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    handler.removeCallbacksAndMessages(null)
                }
            }
        )

        super.onCreateOptionsMenu(menu, inflater)
    }
}
