package com.mayurdw.fibretracker.data

import javax.inject.Inject

class AddEntryUseCase @Inject constructor(
    private val addEntryUseCase: IAddEntryUseCase
) : IAddEntryUseCase {
}
