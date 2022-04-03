package com.honeycomb.developerapplication_mvvm.presentation.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.honeycomb.developerapplication_mvvm.R
import com.honeycomb.developerapplication_mvvm.data.product.remote.dto.ProductResponse
import com.honeycomb.developerapplication_mvvm.databinding.FragmentProductsBinding
import com.honeycomb.developerapplication_mvvm.utils.AndroidUtils
import dagger.hilt.android.AndroidEntryPoint
import  com.honeycomb.developerapplication_mvvm.domain.common.result.Result

@AndroidEntryPoint
class ProductsFragment : Fragment(R.layout.fragment_products), IProductsItemClick {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private lateinit var productsViewModel: ProductsViewModel
    private lateinit var productsAdapter : ProductsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductsBinding.bind(view)

        productsViewModel = ViewModelProvider(requireActivity()).get(ProductsViewModel::class.java)

        fetchAllProducts()
    }

    private fun fetchAllProducts() {
        productsViewModel.movieList.observe(requireActivity(), Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data?.products.let { list ->
                        productsAdapter = ProductsAdapter(requireContext(),list!!,this)

                        binding.recyclerView.apply {
                            adapter = productsAdapter
                            layoutManager  = GridLayoutManager(requireContext(),2)
                        }
                    }
                    binding.dataLoading.visibility = View.GONE
                }

                Result.Status.ERROR -> {
                    result.message?.let {
                        val snackBar = AndroidUtils.showError(requireView(),it)
                        snackBar.show()
                        AndroidUtils.shakeView(snackBar.view, 10, 0)
                    }
                    binding.dataLoading.visibility = View.GONE
                }

                Result.Status.LOADING -> {
                    binding.dataLoading.visibility = View.VISIBLE
                }
            }

        })
    }

    override fun itemClicked(product: ProductResponse) {
        val action = ProductsFragmentDirections.actionHomeFragmentToDetailFragment(product)
        requireView().findNavController().navigate(action)
    }
}