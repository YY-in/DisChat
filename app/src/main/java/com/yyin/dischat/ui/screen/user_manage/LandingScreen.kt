package com.yyin.dischat.ui.screen.user_manage

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyin.dischat.domain.repository.AuthResult
import com.yyin.dischat.ui.component.user_manage.ButtonColumn
import com.yyin.dischat.ui.component.user_manage.DisplayBoard
import com.yyin.dischat.ui.component.user_manage.DisplayLogo
import com.yyin.dischat.ui.component.user_manage.WelcomeText
import com.yyin.dischat.viewmodel.UserManageViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.getViewModel

@Composable
fun LandingScreen(
    onClickRegister: () -> Unit,
    oneClickLogin: ()-> Unit,
    onAuthorized: () -> Unit,
    viewModel: UserManageViewModel = getViewModel()
){
    // show toast when we get the error message
    val context = LocalContext.current
    // run the suspend function in a composable function
    // collect  the flow  safely
    LaunchedEffect(viewModel,context){
        viewModel.authResult.collect{  result ->
            when(result){
                is AuthResult.Authorized -> {
                     onAuthorized()
                }
                is AuthResult.UnAuthorized ->{
                    Toast.makeText(
                        context,
                        "Welcome to DisChat",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is AuthResult.UnKnowError ->{
                    Toast.makeText(
                        context,
                        "An unknown error occurred",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

        Column {
            Spacer(modifier = Modifier.paddingFromBaseline(100.dp, 40.dp))
            DisplayLogo()
            Spacer(modifier = Modifier.paddingFromBaseline(80.dp, 80.dp))
            DisplayBoard(modifier = Modifier.padding(1.dp))
            Spacer(modifier = Modifier.paddingFromBaseline(60.dp, 30.dp))
            WelcomeText()
            Spacer(modifier = Modifier.paddingFromBaseline(40.dp, 20.dp))
            ButtonColumn(
                modifier = Modifier.padding(horizontal = 20.dp),
                onClickRegister = onClickRegister,
                oneClickLogin = oneClickLogin
            )
    }
}