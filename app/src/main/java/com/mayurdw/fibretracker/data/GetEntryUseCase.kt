package com.mayurdw.fibretracker.data

import javax.inject.Inject

class GetEntryUseCase @Inject constructor(
    private val entryDao: EntryDao
) : IGetEntryUseCase {
}
