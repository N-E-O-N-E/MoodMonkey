package com.example.moodmonkey.views

import android.R.attr.fontStyle
import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodmonkey.viewModel.MoodEntryViewModel


@Composable
fun NewEntryView(
    modifier: Modifier = Modifier) {


    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {

    Text("New Entry", modifier = Modifier.padding(all = 10.dp))


        //TODO: Slider


        //TODO: ActivityList LazyRow




        //TODO: Content Multiline TextField

        TextField(value = "",
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(0.65f).padding(all = 10.dp),
            shape = RectangleShape,
            singleLine = false,
            placeholder = { Text("KatanaNote hinzufügen") }
        )


        //TODO: Save Button -> Creates new MoodEntry with Date





    }
}

@Preview(showSystemUi = true, showBackground = true, name = "NewEntry")
@Composable
private fun NewEntryViewPreview() {
    NewEntryView()
}