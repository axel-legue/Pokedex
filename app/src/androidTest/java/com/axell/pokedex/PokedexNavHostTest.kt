package com.axell.pokedex

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokedexNavHostTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: NavHostController

    @Before
    fun setupRallyNavHost() {
        composeTestRule.setContent {
            navController = rememberNavController()
            PokedexNavHost(navController = navController)
        }
    }

    @Test
    fun rallyNavHost() {
        composeTestRule
            .onNodeWithContentDescription("Home Screen")
            .assertIsDisplayed()
    }

}