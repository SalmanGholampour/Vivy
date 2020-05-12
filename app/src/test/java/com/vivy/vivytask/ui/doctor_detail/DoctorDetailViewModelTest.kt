package com.vivy.vivytask.ui.doctor_detail

import com.vivy.vivytask.data.DoctorData
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DoctorsListViewModelTest {


    private lateinit var viewModel: DoctorDetailViewModel

    @Before
    fun setUp() {

        viewModel = DoctorDetailViewModel()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {

    }

    @Test
    fun setDoctorDataTest() {
        val customId = "customId"
        val customName = "customName"
        val customAddress = "customAddress"
        val customRate = 5.0
        val mockDoctor = mock(DoctorData::class.java)
        `when`(mockDoctor.photoId).thenReturn(customId)
        `when`(mockDoctor.name).thenReturn(customName)
        `when`(mockDoctor.address).thenReturn(customAddress)
        `when`(mockDoctor.rating).thenReturn(customRate)
       viewModel.setDoctorData(mockDoctor)

        assert(viewModel.photoId.get()==customId)
        assert(viewModel.name.get()==customName)
        assert(viewModel.address.get()==customAddress)
        assert(viewModel.rating.get()==customRate)
    }


}