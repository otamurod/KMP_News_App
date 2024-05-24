//
//  AboutDeviceScreen.swift
//  iosApp
//
//  Created by Otamurod Safarov on 24/05/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct AboutDeviceScreen: View {
    var body: some View {
        NavigationView {
            AboutDeviceView().navigationTitle("About Device")
        }
    }
}

struct AboutDeviceScreen_Previews: PreviewProvider {
    static var previews: some View {
        AboutDeviceScreen()
    }
}
