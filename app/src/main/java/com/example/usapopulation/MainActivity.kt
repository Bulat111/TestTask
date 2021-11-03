package com.example.usapopulation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

/*
Запрос https://datausa.io/api/data?drilldowns=Nation&measures=Population
// TODO Необходимо реализовать выше запрос (авторизация не требуется) и вывести значение на экран ввиде списка элементов и
// TODO сделать возможность сортировать список по годам в порядке возрастания/убывания

Желательно но не обязательно разработать обработку сетевых ошибок с выводом дружественного пользователю сообщения на экран
*/

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}