package com.redevrx.example_test

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.redevrx.example_test.ui.theme.ExampleTestTheme
import kotlinx.coroutines.delay
import kotlin.math.log
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleTestTheme() {
              ExampleTest()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExampleTest(){
    var txtInput = remember { mutableStateOf(TextFieldValue("")) }
    var listData = remember { mutableStateListOf<ListData?>() }

    var listItems = remember { mutableStateListOf<ListData?>() }

 Box(modifier = Modifier.fillMaxSize()) {
     Column {
         Spacer(modifier = Modifier.height(20.dp))
         Row() {
             TextField(
                 modifier = Modifier,
                 value = txtInput.value,
                 onValueChange = {
                     txtInput.value = it
                 })

             Button(
                 enabled = txtInput.value.text.isNotEmpty() && txtInput.value.text.isNotBlank(),
                 onClick = {
                     if(txtInput.value.text.isNotEmpty() && txtInput.value.text.isNotBlank()){
                         listData.add(ListData(name = txtInput.value.text, isCheck = mutableStateOf(false)))
                         txtInput.value = TextFieldValue("")
                     }
                 },
                 colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFe23425), disabledContainerColor =  Color(0xFF50e23425)),
                 modifier = Modifier.padding(1.dp)) {
                 Text(text = "Add")
             }
         }
         Spacer(modifier = Modifier.height(30.dp))
         ///list data
         if(!listData.isNullOrEmpty()){
             LazyColumn {
                 itemsIndexed(listData){_, it ->
                    Row() {
                        if (it != null) {
                            Text(text = it.name, modifier = Modifier.fillParentMaxWidth(fraction = .8f))
                        }
                        if (it != null) {
                            Checkbox(checked = it.isCheck.value, onCheckedChange = { valueChange ->
                                it.isCheck.value = valueChange
                            })
                        }
                }
                 }

             }
         }
     }
     ///button remove list data
     Button(modifier = Modifier
         .fillMaxWidth(.8f)
         .align(alignment = Alignment.BottomCenter),
         onClick = {
             if (!listData.isNullOrEmpty() && listData.size > 0){
                 listData.removeIf {
                     it -> it?.isCheck?.value == true
                 }
             }
         }) {
         Text(text = "Delete")
     }
 }
}

data class ListData(val name:String,val isCheck: MutableState<Boolean>)