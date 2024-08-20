package ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.constants

import org.openqa.selenium.By

object TagTG {
    // P_O1
    val A_BUTTON_PHONE_NUMBER = By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div[2]/button[1]")

    // P_O2
    val B_FIELD_PHONE_NUMBER = By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[3]/div[2]/div[1]")
    val B_BUTTON_NEXT = By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[3]/button[1]/div")

    // P_O3
    val C_DIV_ACTIVE = By.xpath("/html/body/div[1]/div/div[2]/div[4]")
    val C_FIELD_CODE = By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div[3]/div/input")

    // P_O4
    val D_FIELD_SECRET_CODE = By.xpath("/html/body/div[1]/div/div[2]/div[5]/div/div[2]/div/input[2]")
    val D_DIV_TAB = By.xpath("/html/body/div[1]/div/div[2]/div[5]")
    val D_BUTTON_NEXT = By.xpath("/html/body/div[1]/div/div[2]/div[5]/div/div[2]/button")

    // P_O5 Страница открытого чата
    val P_05_BUTTON_SCROLL_DOWN = By.xpath("/html/body/div[1]/div/div[2]/div[1]/div/div[4]/div/button[1]")
}