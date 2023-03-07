package com.example.backpackapp.util

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.util.regex.Pattern

object Validate {
        fun checkEmail(
            edt: EditText, tv: TextView
        ) {
            edt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (Pattern.matches(
                            "\\w{0,1000}" + "@" + "gmail" + "\\." + "com",
                            s.toString()
                        ) || Pattern.matches(
                            "\\w{0,1000}" + "\\." + "\\w{0,1000}" + "@" + "gmail" + "\\." + "com",
                            s.toString()
                        ) && !Pattern.matches("\t", s.toString())
                    ) {
                        tv.visibility = View.GONE
                        Parameters.IS_EMPTY.also { tv.text = it }
                    } else {
                        tv.visibility = View.VISIBLE
                        Parameters.CHECK_FORMAT_EMAIL_REGISTER.also {
                            tv.text = it
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
        }

        fun checkPass(
            edt: EditText, tv: TextView
        ) {
            edt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (Pattern.matches(
                            "[a-zA-Z_0-9]{6,1000}",
                            s.toString()
                        ) && !Pattern.matches("\t", s.toString())
                    ) {
                        tv.visibility = View.GONE
                        "".also { tv.text = it }
                    } else {
                        tv.visibility = View.VISIBLE
                        Parameters.CHECK_PASSWORD.also { tv.text = it }
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
        }
}