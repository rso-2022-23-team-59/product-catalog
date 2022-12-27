INSERT INTO products (name, image, description) VALUES ('SAMSUNG pametni telefon Galaxy A53 5G 6GB/128GB, Black', 'https://www.bigbang.si/upload/catalog/product/926591/thumb/54640365a7a171d2496788563bf991ca515acc0f10b0fca54e_6234968dbe23_640x640r.webp', 'Mobilni telefon Galaxy A53 5G odlikuje vrhunski SuperAMOLED zaslon s kaljenim steklom Gorilla Glass 5, visoko ločljivostjo Full HD+ in frekvenco osveževanja kar 120 Hz. Poganja ga zmogljiv 8-jedrni 5nm procesor Samsung Exynos 1280 s povezljivostjo 5G, zmogljivost pomnilnika 6 GB/128 GB z možnostjo razširitve do 1 TB. Nameščen je napreden operacijski sistem Android z uporabniškim vmesnikom One UI 4.1. Odlikuje pa ga še dvojni SIM, hibridna reža za pomnilniške kartice microSDXD. Opremljen je s štirimi kamerami ločljivosti 64 + 12 + 5 + 5 MP, sprednjo kamero 32 MP. Baterija s kapaciteto 5000 mAh s podporo za hitro polnjenje 25 W, čitalnik prstnih odtisov, tehnologija NFC, zaščita IP67.');
INSERT INTO products (name, image, description) VALUES ('HUAWEI pametna ura Watch GT 2 Pro (46mm), črna', 'https://www.mimovrste.com/i/91600071/1000/1000', 'Safirna številčnica za odpornost proti obrabi se brezhibno poveže s titanovim okvirjem za lahek in trden dizajn. Sijajni in koži prijazen keramični hrbet zagotavlja udobno nošenje. S prefinjeno izdelavo HUAWEI WATCH GT 2 Pro razkriva prefinjen okus s popolnim ravnovesjem umetnosti in tehnologije.');
INSERT INTO products (name, image, description) VALUES ('STIHL motorna žaga MS 170', 'https://unicommerce.si/media/image/5550/ms-170.jpg', 'MS 170 je STIHL-ov vstopni model motorne žage. Primerna za domačo uporabo, predvsem za pripravo drv za ogrevanje ali lažja gradbena dela. Žaga je opremljena z 2-MIX motorjem in mečem dolžine 35cm.');
INSERT INTO products (name, image, description) VALUES ('APPLE slušalke AirPods 2 (MV7N2ZM/A)', 'https://cdn2.harveynorman.si/media/mf_webp/png/media/catalog/product/cache/b3b166914d87ce343d4dc5ec5117b502/a/p/apple_airpods2.webp', 'Apple slušalke AirPods2 s polnilnim ovitkom se takoj povežejo ter vas potopijo v bogat, visoko kakovosten zvok. Apple slušalke AirPods2 s polnilnim ovitkomApple slušalke AirPods2 omogočajo neprimerljivo brezžično izkušnjo.');
INSERT INTO products (name, image, description) VALUES ('MAKITA akumulatorski bluetooth radio DMR115', 'https://www.center-orodja.si/wp-content/uploads/2021/12/MAK-DMR115_BT_RADIO.jpg', '7 nastavljivih zvočnih načinov z LED barvno indikacijo, da ustrezajo glasbeni zvrsti s prilagoditvijo visokih, srednjih in nizkih tonov.');
INSERT INTO products (name, image, description) VALUES ('BOSCH nadreskar GKF 550', 'https://www.berco.si/wp-content/uploads/2020/11/GKF550-elektricni-robni-rezkalnik-Bosch-768x768.webp', 'Električni robni rezkalnik GKF 550 BOSCH');
INSERT INTO products (name, image, description) VALUES ('CANDY pralno-sušilni stroj CSWS4852DWE/1-S', 'https://www.bigbang.si/upload/catalog/product/925586/thumb/33142bda22b3d3ee4dadd801de9d99d3c6f0356725a968a0d6_6217e079ef53f_640x640r.webp', 'Pralno sušilni stroj je opremljen s funkcijo KG detektor, ki oceni količino perila ter prilagodi čas pranja in porabo vode ter energije, MPS sistem pa omogoča boljši učinek pranja pri nižjih temperaturah. Poleg klasičnega upravljanja ima stroj na voljo elektronsko upravljanje preko NFC sistema s pomočjo aplikacije Simply-fi.');

-- Multiple prices for product 1 in store 1
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (1, 1, 100, '2022-12-27 11:34:00.000000');
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (1, 1, 106, '2022-12-27 12:50:00.000000');
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (1, 1, 99, '2022-12-27 13:29:00.000000');

-- Multiple prices for product 1 in store 2
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (1, 2, 120, '2022-12-27 11:35:00.000000');
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (1, 2, 136, '2022-12-28 11:35:00.000000');
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (1, 2, 142, '2022-12-29 11:35:00.000000');

-- Multiple prices for product 1 in store 3
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (1, 3, 80, '2022-12-27 11:36:00.000000');
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (1, 3, 90, '2022-12-27 11:37:00.000000');
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (1, 3, 65, '2022-12-27 11:38:00.000000');

-- Multiple prices for product 1 in store 4
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (1, 4, 150, '2022-12-27 11:37:00.000000');
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (1, 4, 151, '2022-12-27 12:37:00.000000');
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (1, 4, 177, '2022-12-27 13:37:00.000000');

-- Product
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (2, 1, 100, '2022-12-27 11:38:00.000000');
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (2, 2, 80, '2022-12-27 11:39:00.000000');
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (2, 3, 210, '2022-12-27 11:40:00.000000');
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (3, 1, 120, '2022-12-27 11:41:00.000000');
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (3, 2, 80, '2022-12-27 11:42:00.000000');
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (4, 1, 120, '2022-12-27 11:43:00.000000');
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (4, 2, 80, '2022-12-27 11:44:00.000000');
INSERT INTO productstores (product_id, store_id, price, timestamp) VALUES (4, 3, 200, '2022-12-27 11:45:00.000000');