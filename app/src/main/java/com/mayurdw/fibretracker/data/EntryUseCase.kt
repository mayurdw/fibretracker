package com.mayurdw.fibretracker.data

import javax.inject.Inject

class EntryUseCase @Inject constructor(
    private val entryUseCase: IEntryUseCase
) : IEntryUseCase {
}
