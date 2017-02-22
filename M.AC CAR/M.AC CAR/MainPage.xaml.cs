using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Phone.Controls;
using System.IO.IsolatedStorage;
using System.Xml.Serialization;
using System.IO;
using System.Xml;

namespace M.AC_CAR
{
    public partial class MainPage : PhoneApplicationPage
    {
        // Constructor
        public MainPage()
        {
            InitializeComponent();
            listBox_fiyat.SelectionChanged += new SelectionChangedEventHandler(listBox_fiyat_SelectionChanged);
            listBox_marka.SelectionChanged += new SelectionChangedEventHandler(listBox_marka_SelectionChanged);
            listBox_model.SelectionChanged += new SelectionChangedEventHandler(listBox_model_SelectionChanged);
            listBox_yil.SelectionChanged += new SelectionChangedEventHandler(listBox_yil_SelectionChanged);
        }

        void listBox_yil_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            secim(listBox_yil);
        }

        void listBox_model_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            secim(listBox_model);
        }

        void listBox_marka_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            secim(listBox_marka);
        }

        public void secim(ListBox l)
        {
            /*ListBox[] dizi = { listBox_fiyat, listBox_marka, listBox_model, listBox_yil };
            for (int i = 0; i < dizi.Length; i++)
            {
                dizi[i].SelectedIndex = l.SelectedIndex;
            }*/
        }

        void listBox_fiyat_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            secim(listBox_fiyat);
        }
        List<kayitlar> data2;
        public void verileri_getir()
        {
            listBox_yil.Items.Clear();
            listBox_model.Items.Clear();
            listBox_marka.Items.Clear();
            listBox_fiyat.Items.Clear();
            try
            {
                using (IsolatedStorageFile myIsolatedStorage = IsolatedStorageFile.GetUserStoreForApplication())
                {
                    using (IsolatedStorageFileStream stream = myIsolatedStorage.OpenFile("veriler.xml", FileMode.Open))
                    {
                        XmlSerializer serializer = new XmlSerializer(typeof(List<kayitlar>));
                        data2 = (List<kayitlar>)serializer.Deserialize(stream);
                        for (int i = 0; i < data2.Count; i++)
                        {
                            listBox_fiyat.Items.Add(data2[i].FIYAT);
                            listBox_marka.Items.Add(data2[i].MARKA);
                            listBox_model.Items.Add(data2[i].MODEL);
                            listBox_yil.Items.Add(data2[i].YIL);
                        }
                    }
                }
            }
            catch (Exception)
            {
            }
        }

        public void verileri_yaz()
        {
            XmlWriterSettings xmlWriterSettings = new XmlWriterSettings();
            xmlWriterSettings.Indent = true;

            using (IsolatedStorageFile myIsolatedStorage = IsolatedStorageFile.GetUserStoreForApplication())
            {
                using (IsolatedStorageFileStream stream = myIsolatedStorage.OpenFile("veriler.xml", FileMode.Create))
                {
                    XmlSerializer serializer = new XmlSerializer(typeof(List<kayitlar>));
                    using (XmlWriter xmlWriter = XmlWriter.Create(stream, xmlWriterSettings))
                    {
                        serializer.Serialize(xmlWriter, verileri_yaz_bilgiler());
                    }
                }
            }
            MessageBox.Show("KAYIT BAŞARILI");
            verileri_getir();
        }

        public List<kayitlar> verileri_yaz_bilgiler()
        {
            List<kayitlar> data = new List<kayitlar>();
            try
            {
                for (int i = 0; i < data2.Count; i++)
                {
                    data.Add(new kayitlar() { FIYAT = int.Parse(data2[i].FIYAT.ToString()), MARKA = data2[i].MARKA, MODEL = data2[i].MODEL, YIL = data2[i].YIL });
                }
                data.Add(new kayitlar() { FIYAT = int.Parse(txt_fiyat.Text), MARKA = txt_marka.Text, MODEL = txt_model.Text, YIL = int.Parse(txt_yil.Text) });
            }
            catch (Exception)
            {
                data.Add(new kayitlar() { FIYAT = int.Parse(txt_fiyat.Text), MARKA = txt_marka.Text, MODEL = txt_model.Text, YIL = int.Parse(txt_yil.Text) });
            }
            return data;
        }

        private void btn_kaydet_Click(object sender, RoutedEventArgs e)
        {
            verileri_yaz();
        }

        private void btn_göster_Click(object sender, RoutedEventArgs e)
        {
            verileri_getir();
        }

        public class kayitlar
        {
            string marka;
            string model;
            int yil;
            int fiyat;

            public string MARKA
            {
                get { return marka; }
                set { marka = value; }
            }

            public string MODEL
            {
                get { return model; }
                set { model = value; }
            }

            public int YIL
            {
                get { return yil; }
                set { yil = value; }
            }

            public int FIYAT
            {
                get { return fiyat; }
                set { fiyat = value; }
            }
        }
    }
}