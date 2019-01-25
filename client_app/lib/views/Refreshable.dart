import 'package:flutter/widgets.dart';

abstract class Refreshable extends StatelessWidget {
  void updateState(Map<String, dynamic> json);
  void createUiAfterClickButton(BuildContext context);
}