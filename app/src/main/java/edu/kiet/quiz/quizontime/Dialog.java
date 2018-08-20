package edu.kiet.quiz.quizontime;

import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatDialogFragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;


public class Dialog extends AppCompatDialogFragment {
    private EditText NOQ;
    private DialogListener listener;
    String choice;
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.lauout_dialog,null);
        NOQ=view.findViewById(R.id.N_O_Q);

        builder.setView(view)
                .setTitle("** Range of Question Values is 1-50")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



                choice = getArguments().getString("choice");
                String noofques=NOQ.getText().toString();
                if(!noofques.isEmpty()) {
                    int noq = Integer.parseInt(noofques);
                    listener.applyTexts(noofques);

                    if(noq>50)
                    {
                     Toast.makeText(Dialog.this.getActivity(),"Value should be less than 50", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        switch (choice) {
                            case "Java":
                                Intent java = new Intent(Dialog.this.getActivity(), MainActivity.class);
                                java.putExtra("NOQ", noq);
                                startActivity(java);
                                break;

                            case "Clang":
                                Intent C = new Intent(Dialog.this.getActivity(), CActivity.class);
                                C.putExtra("NOQ", noq);
                                startActivity(C);
                                break;

                            case "cb":
                                Intent Cf = new Intent(Dialog.this.getActivity(), ComputerBasic.class);
                                Cf.putExtra("NOQ", noq);
                                startActivity(Cf);
                                break;

                            case "aptitude":
                                Intent ap = new Intent(Dialog.this.getActivity(), Aptitude.class);
                                ap.putExtra("NOQ", noq);
                                startActivity(ap);
                                break;

                            case "python":
                                Intent py = new Intent(Dialog.this.getActivity(), Python.class);
                                py.putExtra("NOQ", noq);
                                startActivity(py);
                                break;

                            case "lr":
                                Intent lr = new Intent(Dialog.this.getActivity(), LRActivity.class);
                                lr.putExtra("NOQ", noq);
                                startActivity(lr);
                                break;

                            case "c++":
                                Intent cpp = new Intent(Dialog.this.getActivity(), CPP.class);
                                cpp.putExtra("NOQ", noq);
                                startActivity(cpp);
                                break;

                            case "js":
                                Intent js = new Intent(Dialog.this.getActivity(), JavaScript.class);
                                js.putExtra("NOQ", noq);
                                startActivity(js);
                                break;

                        }
                    }
                }
                else
                {
                 Toast.makeText(Dialog.this.getActivity(),"enter the value",Toast.LENGTH_SHORT).show();
                }


            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener=(DialogListener)context;
        }catch (Exception e){
            throw new ClassCastException(context.toString() +
                    "implement dialog listener");
        }

    }

    public interface DialogListener{
        void applyTexts(String noofques);


    }
}
