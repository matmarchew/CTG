import 'package:client_app/state/Model.dart';
import 'package:client_app/views/main/action/ButtonAction.dart';
import 'package:client_app/views/main/action/ButtonsActionSection.dart';
import 'package:client_app/views/main/betting_card/BettingCardSection.dart';
import 'package:client_app/views/main/betting_tile/BettingTileSection.dart';
import 'package:client_app/views/main/board/BoardSection.dart';
import 'package:client_app/views/main/cube/CubeSection.dart';
import 'package:flutter/material.dart';

class MainView extends StatelessWidget {
  final Model model;
  final List<ButtonAction> buttons;

  MainView(this.model, this.buttons);

  Model getModel() {
    return model;
  }

  @override
  Widget build(BuildContext context) {
    return Container(child: LayoutBuilder(builder: (content, constraints) {
      return Center(
        child: Column(children: [
          CubeSection(),
          BettingTileSection(),
          BoardSection(),
          BettingCardSection(),
          ButtonsActionSection(buttons)
        ]),
      );
    }));
  }
}
