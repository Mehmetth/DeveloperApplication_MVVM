package com.honeycomb.developerapplication_mvvm.presentation.productsdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.honeycomb.developerapplication_mvvm.R
import com.honeycomb.developerapplication_mvvm.data.product.remote.dto.ProductDetailResponse
import com.honeycomb.developerapplication_mvvm.databinding.FragmentProductDetailBinding
import com.honeycomb.developerapplication_mvvm.utils.AndroidUtils
import com.honeycomb.developerapplication_mvvm.domain.common.result.Result
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    val args : ProductDetailFragmentArgs by navArgs()

    private lateinit var productDetailViewModel: ProductDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductDetailBinding.bind(view)

        productDetailViewModel = ViewModelProvider(requireActivity()).get(ProductDetailViewModel::class.java)

        fetchProductDetail(args.productDetail!!.product_id)

        binding.productDetailBackButton.setOnClickListener {
            binding.productDetailName.text = ""
            binding.productDetailPriceDetail.text = ""
            binding.productDetailDescriptionDetail.text = ""

            requireView().findNavController().popBackStack()
        }
    }

    private fun fetchProductDetail(product_id: String) {
        productDetailViewModel.fetchProductDetail(product_id)

        productDetailViewModel.mProduct.observe(requireActivity(), Observer { result ->

            when (result.status) {
                Result.Status.SUCCESS -> {
                    fillProductItem(result.data!!)
                    binding.dataLoading.visibility = View.GONE
                }

                Result.Status.ERROR -> {
                    result.message?.let {
                        val snackBar = AndroidUtils.showError(_binding!!.root,it)
                        snackBar.show()
                        AndroidUtils.shakeView(snackBar.view, 10, 0)

                        Picasso
                            .get()
                            .load(R.drawable.not_found)
                            .into(binding.productDetailImage)
                    }
                    binding.dataLoading.visibility = View.GONE
                }

                Result.Status.LOADING -> {
                    binding.dataLoading.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun fillProductItem(productDetailResponse: ProductDetailResponse){
        binding.productDetailName.text = productDetailResponse.name
        Picasso
            .get()
            .load(productDetailResponse.image)
            .placeholder(R.drawable.image_load)
            .error(R.drawable.not_found)
            .into(binding.productDetailImage)
        binding.productDetailPriceDetail.text = productDetailResponse.price.toString()
        binding.productDetailDescriptionDetail.text = productDetailResponse.description
    }
}