import 'package:client_app/views/main/action/ButtonAction.dart';
import 'package:flutter/material.dart';

class ButtonsActionSection extends StatelessWidget {
  final List<ButtonAction> buttons;

  ButtonsActionSection(this.buttons);

  @override
  Widget build(BuildContext context) {
    return Expanded(flex: 2, child: Row(children: buttons));
  }
}
