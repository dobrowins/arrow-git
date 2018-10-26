package com.dobrowins.arrowktplayground

import android.content.Context
import android.widget.Toast

/**
 * @author: Artem Dobrobinsky
 */

fun Context.toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()