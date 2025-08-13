package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.FoodItem

enum class CommonFoods(val foodItem: FoodItem) {
    OATS(FoodItem("Oats", 50, 120)),
    CHIA(FoodItem("Chia", 10, 344)),
    GLUTEN_FREE_BREAD(FoodItem("GF Bread", 70, 154)),
    RICE_CAKE(FoodItem("Rice Cake", 35, 35)),
    BANANA(FoodItem("Banana", 120, 26)),
    POTATO(FoodItem("Potato", 110, 22)),
    BEAN(FoodItem("Beans", 50, 34)),
    CARROT(FoodItem("Carrot", 50, 32)),
    PSYLLIUM_HUSK(FoodItem("Psyllium Husk", 2, 800))
}
