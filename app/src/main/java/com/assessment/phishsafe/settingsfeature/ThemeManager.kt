import android.content.Context

object ThemeManager {
    private const val PREF_NAME = "theme_prefs"
    private const val KEY_DARK_THEME = "dark_theme"

    fun isDarkThemeEnabled(context: Context): Boolean {
        val sharedPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPrefs.getBoolean(KEY_DARK_THEME, false)
    }

    fun setDarkThemeEnabled(context: Context, enabled: Boolean) {
        val sharedPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putBoolean(KEY_DARK_THEME, enabled)
        editor.apply()
    }
}
