package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.data.database.AppDao
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test

class GetEntryUseCaseTest {
    private lateinit var useCase: GetEntryUseCase

    @RelaxedMockK
    lateinit var dao: AppDao

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        useCase = GetEntryUseCase(dao)
    }

    @Test
    fun `use case is not null`() {
        assertNotNull(useCase)
    }
}
