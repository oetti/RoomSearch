package extras;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogBuilder {

	public static void beendenDialog(Context context) {
		AlertDialog.Builder beendenDialog = new AlertDialog.Builder(context);
		beendenDialog.setTitle("Beenden");
		beendenDialog.setMessage("Willst du Room Search wirklich beenden?");
		beendenDialog.setPositiveButton("ja", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface arg0, int arg1) {
			ActivityRegistry.finishAll();
		}});
		beendenDialog.setNegativeButton("nein", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface arg0, int arg1) {
			// tue nicht
		}});
		beendenDialog.show();
	}
	
	public static AlertDialog.Builder getDialog(Context context, String titel, String text) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setTitle(titel);
		dialog.setMessage(text);
		return dialog;
	}
}
