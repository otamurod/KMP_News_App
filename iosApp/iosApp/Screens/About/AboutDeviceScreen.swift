//
//  AboutDeviceScreen.swift
//  iosApp
//
//  Created by Otamurod Safarov on 24/05/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct AboutDeviceScreen: View {
    @Environment(\.dismiss)
    private var dismiss

    var body: some View {
        NavigationView {
            AboutDeviceView()
                .navigationTitle("About Device")
                .navigationBarTitleDisplayMode(.inline)
                .toolbar {
                    ToolbarItem(placement: .primaryAction) {
                        Button {
                            dismiss()
                        }
                        label: {
                            Text("Done").bold().font(.footnote)
                        }
                    }
                }
        }
    }
}

struct AboutDeviceScreen_Previews: PreviewProvider {
    static var previews: some View {
        AboutDeviceScreen()
    }
}
