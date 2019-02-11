import 'package:client_app/action/Action.dart';
import 'package:flutter/material.dart';

class ButtonAction extends StatelessWidget {
  final Action action;
  final BuildContext context;

  ButtonAction(this.action, this.context);

  doSpecialAction() {
    action.doSpecialBehavior(context);
  }

  @override
  Widget build(BuildContext context) {
    return Expanded(
        flex: 1,
        child: Container(
            child: Center(
                child: MaterialButton(
          onPressed: doSpecialAction,
          color: Colors.red,
        ))));
  }
}
