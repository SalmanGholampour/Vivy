package com.vivy.vivytask.ui.doctors_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.vivy.vivytask.BuildConfig
import com.vivy.vivytask.data.DoctorData
import com.vivy.vivytask.data.DoctorsResponse
import com.vivy.vivytask.data.repository.RepositoryInterface
import com.vivy.vivytask.rx.TestSchedulerProvider
import com.vivy.vivytask.util.getUrl
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

internal const val MyUtilsKt = "com.vivy.vivytask.util.UtilsKt"

@RunWith(PowerMockRunner::class)
@PrepareForTest(BuildConfig::class, fullyQualifiedNames = [MyUtilsKt])
class DoctorsListViewModelTest {
    @Mock
    lateinit var repository: RepositoryInterface

    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var mTestScheduler: TestScheduler

    private lateinit var viewModel: DoctorsListViewModel

    @Before
    fun setUp() {
        compositeDisposable = CompositeDisposable()
        mTestScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler)
        viewModel = DoctorsListViewModel(repository, compositeDisposable, testSchedulerProvider)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {

    }
    @Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    @Test
    fun loadDataTest() {
        val url = "https://vivy.com/interviews/challenges/android/doctors.json"
        PowerMockito.mockStatic(Class.forName(MyUtilsKt))
        `when`(getUrl("")).thenReturn(url)
        val doctor = mock(DoctorData::class.java)
        val doctors = ArrayList<DoctorData>()
        doctors.add(doctor)
        val doctorsResponse = DoctorsResponse("", doctors)

        val spy = spy(viewModel)
        doNothing().`when`(spy).updateList(ArgumentMatchers.anyList(), ArgumentMatchers.anyList())

        `when`(repository.loadDoctors(getUrl(""))).thenReturn(Single.just(doctorsResponse))




        spy.loadData()
        mTestScheduler.triggerActions()

        verify(spy, times(1)).updateList(ArgumentMatchers.anyList(), ArgumentMatchers.anyList())
    }
    @Test
    fun updateRecentDoctorsTest() {
        val spy = spy(viewModel)
        val targetDoctor = mock(DoctorData::class.java)
        val doctor1 = mock(DoctorData::class.java)
        val doctor2 = mock(DoctorData::class.java)
        val doctor3 = mock(DoctorData::class.java)
        val doctor4 = mock(DoctorData::class.java)
        spy.recentDoctors.add(doctor1)
        spy.recentDoctors.add(doctor2)
        spy.recentDoctors.add(doctor3)

        spy.vivyDoctors.add(targetDoctor)
        spy.vivyDoctors.add(doctor4)

        doNothing().`when`(spy).updateList(ArgumentMatchers.anyList(), ArgumentMatchers.anyList())

        spy.updateRecentDoctors(targetDoctor)
        assert(spy.recentDoctors[0]==targetDoctor)
        assert(spy.vivyDoctors.contains(doctor3))
        assert(!spy.vivyDoctors.contains(targetDoctor))
        verify(spy, times(1)).updateList(ArgumentMatchers.anyList(), ArgumentMatchers.anyList())

    }
    @Test
    fun updateListTest() {
        val spy = spy(viewModel)
        val doctor1 = mock(DoctorData::class.java)
        val doctor2 = mock(DoctorData::class.java)
        val doctor3 = mock(DoctorData::class.java)
        val doctor4 = mock(DoctorData::class.java)
        val doctor5 = mock(DoctorData::class.java)
        val doctor6 = mock(DoctorData::class.java)
        val doctor7 = mock(DoctorData::class.java)
        val recentDoctors = ArrayList<DoctorData>()
        val vivyDoctors = ArrayList<DoctorData>()
        recentDoctors.add(doctor1)
        recentDoctors.add(doctor2)
        recentDoctors.add(doctor3)
        vivyDoctors.add(doctor4)
        vivyDoctors.add(doctor5)
        vivyDoctors.add(doctor6)
        vivyDoctors.add(doctor7)

        spy.updateList(recentDoctors,vivyDoctors)
        assert(spy.listViewLiveData.value!!.size==9)
        assert(spy.listViewLiveData.value!![1]==doctor1)
        assert(spy.listViewLiveData.value!![5]==doctor4)


    }

}