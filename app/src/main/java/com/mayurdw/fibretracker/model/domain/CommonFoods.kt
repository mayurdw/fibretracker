package com.mayurdw.fibretracker.model.domain

import com.mayurdw.fibretracker.model.entity.FoodEntity

val CommonFoods = listOf(
    FoodEntity("Oats", 50, 120).apply { id = 1 },
    FoodEntity("Chia", 10, 344).apply { id = 2 },
    FoodEntity("GF Bread", 70, 154).apply { id = 3 },
    FoodEntity("Rice Cake", 35, 35).apply { id = 4 },
    FoodEntity("Banana", 120, 26).apply { id = 5 },
    FoodEntity("Potato", 110, 22).apply { id = 6 },
    FoodEntity("Beans", 50, 34).apply { id = 7 },
    FoodEntity("Carrot", 50, 32).apply { id = 8 },
    FoodEntity("Psyllium Husk", 2, 800).apply { id = 9 }
)
