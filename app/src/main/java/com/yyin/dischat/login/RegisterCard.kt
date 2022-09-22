package com.yyin.dischat.login

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyin.dischat.R
import com.yyin.dischat.ui.theme.*

enum class TabPage {
    Phone, Email
}


@Preview
@Composable
fun RegisterCard() {
    Scaffold(
        topBar = { TopRegisterBar() },
        backgroundColor = DarkColor
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.registerTip_zh),
                style = MaterialTheme.typography.h5,
                fontWeight = W900,
                color = White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .padding(vertical = 15.dp)
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .background(shape = RoundedCornerShape(10), color = TextFieldColor)
            ) {
                RegisterMethodSwitchTab(
                    backgroundColor = TextFieldColor,
                    tabPage = TabPage.Phone,
                    onTabSelected = {/*TODO*/ })
            }
            RegisterTextField(title = "电话号码", "电话号码", {/*TODO*/}, Modifier)

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Indigo700,
                    contentColor = White
                ),
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            ) {

                Text(stringResource(R.string.next_zh))
            }
        }

    }
}

@Composable
fun TopRegisterBar() {
    TopAppBar(
        backgroundColor = BlueGray900,
        title = {
            Text(
                text = stringResource(R.string.register_zh),
                fontWeight = FontWeight.Bold,
                color = White,
                modifier = Modifier.alpha(ContentAlpha.medium)
            )


        },
        navigationIcon = {
            IconButton(onClick = { /* TODO:doSomething() */ }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "return",
                    tint = White.copy(alpha = ContentAlpha.medium)
                )
            }
        },

        )
}


@Composable
fun RegisterTextField(
    title: String,
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.overline,
            fontWeight = W500,
            color = White,
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = 8.dp, top = 20.dp)
        )
        
        TextField(
            value = text,
            onValueChange = onTextChange,
            maxLines = 1,
            shape= RoundedCornerShape(10),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = TextFieldColor,
                textColor = White.copy(ContentAlpha.medium)
            ),
            modifier = modifier.padding(bottom = 8.dp).fillMaxWidth().height(50.dp),
        )
        Text(
            text = stringResource(R.string.privacy_policy_zh),
            style = MaterialTheme.typography.caption,
            color = Teal200,
            modifier = Modifier
                .alpha(ContentAlpha.high)
                .padding(bottom = 8.dp)
        )
        
        
    }

}

@Preview
@Composable
fun PreviewTextField() {
    RegisterTextField(title = "电话号码", "电话号码", {}, Modifier)
}


@Composable
fun RegisterMethodSwitchTab(
    backgroundColor: Color,
    tabPage: TabPage,
    onTabSelected: (tabPage: TabPage) -> Unit,
) {
    TabRow(
        selectedTabIndex = tabPage.ordinal,
        backgroundColor = backgroundColor,

        ) {
        RegisterMethodTab(
            title = stringResource(R.string.phoneNumber_zh),
            onClick = { onTabSelected(TabPage.Phone) })

        RegisterMethodTab(
            title = stringResource(R.string.email_zh),
            onClick = { onTabSelected(TabPage.Email) })
    }

}

@Composable
fun RegisterMethodTab(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        //用于指定父布局的子元素在布局内主轴方向的对齐方式：
        horizontalArrangement = Arrangement.Center,
        //通常用于一个布局在父布局中的对齐方式，可以认为就是用来做居中的；
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = White,
            style = MaterialTheme.typography.caption,
            modifier = modifier.alpha(ContentAlpha.medium)
        )
    }
}

@Composable
//@Preview
fun PreviewRegisterTab() {
    RegisterMethodSwitchTab(
        backgroundColor = TextFieldColor,
        tabPage = TabPage.Phone,
        onTabSelected = {},
    )
}