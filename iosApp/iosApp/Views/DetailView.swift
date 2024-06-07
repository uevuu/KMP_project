//
//  DetailView.swift
//  iosApp
//
//  Created by Nikita Maryin on 28.05.2024.
//

import SwiftUI

private let stringURL =  "https://www.thoughtco.com/thmb/H3Bt0F4hQuAf9yUQ6bwEEKomDjg=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/child-holding-colorful-gum-balls-576720981-5bfeb5c646e0fb6dc20.j"
private let emptyImageUrl =  "https://www.generationsforpeace.org/wp-content/uploads/2018/03/empty-300x240.jpg"

struct AnimatedGradientSquare: View {
    @State private var animateGradient = false

    var body: some View {
        Rectangle()
            .fill(
                LinearGradient(
                    gradient: Gradient(colors: [.white.opacity(0.1), .gray.opacity(0.8), .white.opacity(0.1)]),
                    startPoint: animateGradient ? .leading : .trailing,
                    endPoint: animateGradient ? .trailing : .leading
                )
            )
           .onAppear {
                withAnimation(Animation.easeInOut(duration: 1).repeatForever(autoreverses: true)) {
                    animateGradient.toggle()
                }
            }
    }
}

struct DetailView: View {
    var body: some View {
        ScrollView(showsIndicators: false) {
            ZStack {
                AsyncImage(url: URL(string: stringURL) ?? URL(string: emptyImageUrl)!) {
                    AnimatedGradientSquare()
                }
//                Image(systemName: "photo")
//                    .resizable()
                    .scaledToFit()
                    .background(Color.gray)
                    .padding(5)
                    .cornerRadius(10)
                VStack {
                    Spacer()
                    HStack {
                        Spacer()
                        Button(action: {
                        }) {
                            Image(systemName: "star.fill")
                                .resizable()
                                .frame(width: 28, height: 28)
                        }
                        .foregroundColor(.yellow)
                        .padding(10)
                        .background(.clear)
                        .cornerRadius(14)
                        .overlay(
                            RoundedRectangle(cornerRadius: 14.0)
                                .strokeBorder(.yellow, style: StrokeStyle(lineWidth: 2))
                        )
                    }
                    .padding([.trailing, .bottom], 25)
                }
            }
            
            VStack {
                infoTextView(text: "Борщ по украински, как у бабушки, много букв", font: .title, color: .black)
                infoTextView(text: "Время готовки: 56 минут")
                infoTextView(text: "Количество порций: 5")
                infoTextView(text: "Стоиммость порции: 134 рубля")
                infoTextView(text: "Налейте в кастрюлю холодную воду, выложите мясо и поставьте на средний огонь. Бульон будет вкуснее, если использовать именно мясо на кости. fdsfdfudsfdhsiof dsfhdsi ofhsdiof sdiohfsd oifhsdiof idsohf iosdfhiodshfdsi hfiods fiodsh fiosdhfidosfh dsiofhdsiof hdsfiohdsiof hdsoif hdsiofh dsiof hdsiofh dsoif hdsiof hdsiofh dsiohfiodshfiosdhfio sdhfiohdsiof hsdiofh dsiofh sdoifh oisdhfois fhoidsh fio sdh foisdhfoids hoifhsdoifhdsoifh oifhsdfio dshfioshd fiosdhf iodsfhdoifh sdoif hoi fh dsiofhdsfiosh foishfoidshfio shdf osdhf hsdofihdsiofhsdi fhdifohdsio fhdfiosdhfoi dhfiodshf iodfhiods fhdoisfhdsoi fhdsoifh sdoi fh dsoifhdsoif hsdoifhsdio fhdsoi fhd foid sfisod hfoi sdfhoisfh sdof fdios fios fsfisod ofj sdi", color: .black)
            }
            .padding(.leading, 15)
        }
    }
}


private extension View {
    func infoTextView(text: String,
                      font: Font = .headline,
                      color: Color = .gray) -> some View {
        HStack {
            Text(text)
                .font(font)
                .multilineTextAlignment(.leading)
                .foregroundColor(color)
            Spacer()
        }
        
    }
}

